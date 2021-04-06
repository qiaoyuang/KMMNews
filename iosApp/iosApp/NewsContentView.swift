//
//  NewsContentView.swift
//  iosApp
//
//  Created by 乔禹昂 on 2021/3/30.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import shared

struct NewsContentView: View {
    
    var newsSummary: NewsSummary
    
    @ObservedObject var fetcher: NewsContentFetcher
    
    @State private var remoteImage : UIImage? = nil
    let placeholderOne: UIImage = UIImage()
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical) {
                VStack(alignment: .center) {
                    Text(fetcher.newsContent.title)
                        .font(.title)
                        .padding([.top, .bottom], 20)
                    Text(fetcher.newsContent.summary)
                        .font(.subheadline)
                        .padding([.leading, .trailing], 20)
                    Image(uiImage: self.remoteImage ?? placeholderOne)
                        .resizable()
                        .onAppear(perform: fetchRemoteImage)
                        .frame(width: 384, height: 256)
                    Text(fetcher.newsContent.content)
                        .font(.body)
                        .padding([.top, .bottom, .leading, .trailing], 10)
                    Text(fetcher.newsContent.editor)
                        .font(.footnote)
                        .padding([.top], 10)
                    Text(fetcher.newsContent.date)
                        .font(.footnote)
                }
            }.navigationBarTitle("新闻详情", displayMode: .inline)
        }
    }
    
    init(newsSummary: NewsSummary) {
        self.newsSummary = newsSummary
        fetcher = NewsContentFetcher(newsSummary: newsSummary)
    }
    
    func fetchRemoteImage() {
        guard let url = URL(string: newsSummary.imageUrl) else { return }
        URLSession.shared.dataTask(with: url){ (data, response, error) in
            if let image = UIImage(data: data!) {
                self.remoteImage = image
            } else {
                print(error ?? "")
            }
        }.resume()
    }
    
}

public class NewsContentFetcher: ObservableObject {
    
    private let newsSummary: NewsSummary
    
    @Published var newsContent: NewsContent
    
    func load(_newsSummary: NewsSummary) {
        NetworkRequest.init().getNewsContent(newsSummary: _newsSummary) { (content: NewsContent?, error: Error?) in
            if (content != nil) {
                self.newsContent = content!
            }
        }
    }
    
    init(newsSummary: NewsSummary) {
        self.newsSummary = newsSummary
        self.newsContent = NewsContent(id: "", title: "", summary: "", date: "", imageUrl: "", content: "", editor: "")
        load(_newsSummary: newsSummary)
    }
    
}
