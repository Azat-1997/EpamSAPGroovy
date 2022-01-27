import java.util.*
import static groovy.io.FileType.*
public class FileTask {

   def getSubDirectories(File dir, int maxdepth) {
          int depth = 0
          File node = dir
          def subDirs = []
          Queue<File> q = new LinkedList<>()
          q.add(node)
          // use BFS to take all folders
          while(!q.isEmpty() && depth < maxdepth) {

              q.remove().listFiles().each {it -> if (it.isDirectory()) { 
                                                          q.add(it)
                                                          subDirs << it
                                                      }
                             }
              depth++
          }

          return subDirs
   }

   public static void main(String[] args) {

       File testFile = new File("result2.csv")

       println testFile.name
       println "${testFile.length()} bytes"

     //  testFile.each({it -> println it})

       println "\ntake current directory"
       File directory = new File(".")
       println directory.canonicalPath
       println "\nlist of files (without directories)"
       def getFiles = {dir -> dir.listFiles().each {it -> if(!it.isDirectory()) {
                                     println it
                                   }
                                           
                            } 
                      }     
       
       getFiles.call(directory)
       println "\nlist of subdirectories"
       def getSubDirs = {dir, depth ->
                         dir.traverse(type: DIRECTORIES, maxDepth: depth) {it -> println it}
                        }

       getSubDirs.call(directory, 1)

       // "hand-made" implementation
       getSubDirectories(directory, 1).each {it -> println it}

       }


}
