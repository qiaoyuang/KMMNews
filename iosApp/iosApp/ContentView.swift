import SwiftUI
import shared


struct ContentView: View {
    var body: some View {
        Text(getText()).padding()
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}

func getNetwork() -> Void {
    NetworkRequest.init().getNewsSummaryList { (list: [NewsSummary]?, error: Error?) in
        list?.forEach({ (summary: NewsSummary) in
            print(summary.title)
        })
    }
}

func getText() -> String {
    getNetwork()
    return "第一个 Text"
}
