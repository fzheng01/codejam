public class StockQuote {

   // Given symbol, get HTML
   public static String readHTML(String symbol) {
       In page = new In("http://finance.yahoo.com/q?s=" + symbol);
       String html = page.readAll();
       return html;
   }

   // Given symbol, get current stock price.
   public static double price(String html) {
       int p     = html.indexOf("yfs_l84", 0);      // "yfs_l84" index
       int from  = html.indexOf(">", p);            // ">" index
       int to    = html.indexOf("</span>", from);   // "</span>" index
       String price = html.substring(from + 1, to);
       return Double.parseDouble(price.replaceAll(",", ""));
   }

   // Given symbol, get current stock name.
   public static String name(String html) {
       int p    = html.indexOf("<title>", 0);
       int from = html.indexOf("Summary for ", p);
       int to   = html.indexOf("- Yahoo! Finance", from);
       String name = html.substring(from + 12, to);
       return name;
   }

   // Given symbol, get current date.
   public static String date(String html) {
       int p    = html.indexOf("<span id=\"yfs_market_time\">", 0);
       int from = html.indexOf(">", p);
       int to   = html.indexOf("-", from);        // no closing small tag
       String date = html.substring(from + 1, to);
       return date;
   }


   public static void main(String[] args) {
       String symbol = args[0];
       String html = readHTML(symbol);
       StdOut.printf("%.2f\n", price(html));
       StdOut.println(name(html));
       StdOut.println(date(html));
   }

}