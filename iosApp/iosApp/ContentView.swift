import SwiftUI
import shared


struct ContentView: View {
    
    var body: some View {
        VStack(alignment: .leading) {
            Text("Title").font(.title)
            Image("turtlerock")
            Text("Joshua Tree National Park").font(.subheadline)
        }
        .padding()
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

func getNetwork() -> Void {
    NetworkRequest.init().getNewsSummaryList { (list: [NewsSummary]?, error: Error?) in
        list?.forEach({ (summary: NewsSummary) in
            print(summary.title)
        })
    }
}
