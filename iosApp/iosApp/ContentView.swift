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
        VStack(alignment: .leading) {
            Text(newsSummary.title).font(.title)
            Image(uiImage: self.remoteImage ?? placeholderOne)
                        .onAppear(perform: fetchRemoteImage)
            Text(newsSummary.summary).font(.subheadline)
            Text(newsSummary.date).font(.subheadline)
        }
    }
    
    func fetchRemoteImage() {
        guard let url = URL(string: newsSummary.imageUrl) else { return } //初始化一个字符串常量，作为网络图片的地址
        URLSession.shared.dataTask(with: url){ (data, response, error) in //执行URLSession单例对象的数据任务方法，以下载指定的图片
            if let image = UIImage(data: data!){
                self.remoteImage = image //当图片下载成功之后，将下载后的数据转换为图像，并存储在remoteImage属性中
            } else {
                print(error ?? "") //如果图片下载失败之后，则在控制台输出错误信息
            }
        }.resume() //通过执行resume方法，开始下载指定路径的网络图片
    }
}

extension NewsSummary: Identifiable {
    public var id: String {
        get {
            self.title
        }
    }
}

