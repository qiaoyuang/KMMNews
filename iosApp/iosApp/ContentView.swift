import SwiftUI
import shared


struct ContentView: View {
    
    var body: some View {
        NetworkRequest.init().getNewsSummaryList { (list: [NewsSummary]?, error: Error?) in
            list?.forEach({ (summary: NewsSummary) in
                NewsSummaryRow(newsSummary: <#T##NewsSummary#>)
            })
        }
    }
}

struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView()
    }
}


struct NewsSummaryRow: View {
    
    var newsSummary: NewsSummary
    
    var body: some View {
        VStack(alignment: .leading) {
            Text(newsSummary.title).font(.title)
            Image(newsSummary.imageUrl)
            Text(newsSummary.summary).font(.subheadline)
            Text(newsSummary.date).font(.subheadline)
        }
        
    }
}

extension NewsSummary: Identifiable {
    public var id: String {
        get {
            self.id
        }
    }
}

func getNetwork() -> Void {
    NetworkRequest.init().getNewsSummaryList { (list: [NewsSummary]?, error: Error?) in
        list?.forEach({ (summary: NewsSummary) in
            print(summary.title)
        })
    }
}
