package second

public class FilesDemo {

  public static void createFile(String filename, int minimum, int maximum, int nrow) {

  Random generator = new Random()
  File testFile = new File(filename)
  // create a text
  def data = []
  for(int j=0; j<nrow; j++) {

    data << ((String) generator.nextInt(maximum) + minimum)

  }

  testFile.withWriter("utf-8") {
   writer -> writer.writeLine(data.join('\n'))
          }

  }

  public static void mergeFiles(File[] files, int nrow, String output) {
    File mergedFile = new File(output)
    def iters = []
    // create list of iterators
    files.each {iters << it.iterator()}
    int total
    def row
    // read each file step by step:
    // take first line of each file and repeat procedure for further lines
    
    def title = []
    files.each {title << it.name}
    title << "total"


    mergedFile.withWriter("utf-8") { writer ->
    writer.writeLine title.join(',')
    for (int i = 0; i < nrow; i++) {
        total = 0
        row = []
        iters.each{
                   String elem = it.next()
                   row << elem
                   total += Integer.valueOf(elem)
                  }
        row << (String) total
        
        writer.writeLine row.join(',')
        }

  }
}
  public static void main(String[] args) {

    def generateFiles = {
    
         for(int i=0; i<10; i++) {

             createFile("testFile${i+1}.csv", 1, 200, 1000)
         
                        }

             println("Files has been created or rewrited")
        }

    // read files
    File directory = new File("test")
    File[] files = directory.listFiles()
    int nrow = 1000
    mergeFiles(files, nrow, "mergedFiles.csv")
   
    }
}
