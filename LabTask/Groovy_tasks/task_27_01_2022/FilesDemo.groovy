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

  public static void main(String[] args) {

    def generateFiles = {
    
         for(int i=0; i<10; i++) {

             createFile("testFile${i+1}.csv", 1, 200, 1000)
         
                        }

             println("Files has been created or rewrited")
        }

    // read files
    File directory = new File("test")
    // take list of files 
    File[] files = directory.listFiles() 
    def row = []
    int total = 0
    def matrix = []
    files.each({matrix << it.readLines()})
    
      
    }
}
