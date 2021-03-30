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
    
    var body: some View {
        NavigationView {
            ScrollView(.vertical) {
                VStack(alignment: .leading) {
                    Text(fetcher.newsContent.title).font(.title)
                    Text(fetcher.newsContent.summary).font(.subheadline)
                    // Image()
                    Text(fetcher.newsContent.content)
                    Text(fetcher.newsContent.editor)
                    Text(fetcher.newsContent.date)
                }
            }.navigationBarTitle("新闻详情", displayMode: .inline)
        }
    }
    
    init(newsSummary: NewsSummary) {
        self.newsSummary = newsSummary
        fetcher = NewsContentFetcher(newsSummary: newsSummary)
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
