package ext

import java.io.File
import java.io.FileWriter

fun File.child(childPath: String): File {
    return File(this, childPath)
}

fun File.fileWriter(overwrite: Boolean): FileWriter {
    return FileWriter(this, overwrite)
}

fun FileWriter.newLine() {
    write(System.getProperty("line.separator"))
}