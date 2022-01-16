public class YoutubeQuery {
      
 public YoutubeQuery(String query, Ordering order, ResourceType type, int maxResults) {
       this.query = query;
       this.order = order;
       this.type  = type;
       this.maxResults = maxResults; 
       }

     public String toString() {
         return "";

                          }


}

enum  Ordering {

      DATE ("date"),
      RATING ("rating"),
      RELEVANCE ("relevance"),
      TITLE ("title"),
      VIDEOCOUNT ("videoCount"),
      VIEWCOUNT ("viewCount");
      
      private String field;

      Ordering (String field) {
          this.field = field;        
          }      
       
      public String getField() {
             return field;
          }


      @Override
      public String toString() {
            return field;
          }


}


enum ResourceType {

CHANNEL ("channel"),
PLAYLIST ("playlist"),
VIDEO ("video");

      private String field;

      Ordering (String field) {
          this.field = field;
          }

      public String getField() {
             return field;
          }

      @Override
      public String toString() {
            return field;
          }

}



