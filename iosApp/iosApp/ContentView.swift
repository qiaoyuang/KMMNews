import SwiftUI
import shared


struct ContentView: View {
    
    @ObservedObject var fetcher = NewsSummaryFetcher()
    
    var body: some View {
        NavigationView {
            List(fetcher.newsSummaryList) { summary in
                NewsSummaryRow(newsSummary: summary)
            }.navigationBarTitle("Kotlin 新闻",displayMode: .inline)
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

public class NewsSummaryFetcher: ObservableObject {
    
    @Published var newsSummaryList = [NewsSummary]()
    
    init() {
        load()
        MMKVIosKt.MMKVInitialize()
        // ReadWriteUtil.init().writeAll()
        // ReadWriteUtil.init().readAndPrintAll()
    }
    
    func load() {
        NetworkRequest.init().getNewsSummaryList { (list: [NewsSummary]?, error: Error?) in
            if (list != nil) {
                self.newsSummaryList = list!
            }
        }
    }
}


struct NewsSummaryRow: View {
    
    var newsSummary: NewsSummary
    
    @State private var remoteImage : UIImage? = nil
    let placeholderOne: UIImage = UIImage()
    
    var body: some View {
        NavigationLink(destination: NewsContentView(newsSummary: newsSummary)) {
            VStack(alignment: .leading) {
                Text(newsSummary.title)
                    .font(.title)
                Image(uiImage: self.remoteImage ?? placeholderOne)
                    .resizable()
                    .onAppear(perform: fetchRemoteImage)
                    .frame(width: 384, height: 256)
                Text(newsSummary.summary)
                    .font(.subheadline)
                Text(newsSummary.date)
                    .font(.footnote)
                    .frame(alignment: .trailing)
                    .padding([.top], 10)
            }
        }
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

extension NewsSummary: Identifiable {
    
    public var id: String {
        get {
            self.title
        }
    }
}
