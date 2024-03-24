fun main() {
    val numberOfElements = 10_000_000  // 10 million for example

    // Create instances of the regular class
    /**
     * Total Bytes: 202,405,416
     * Total Classes: 1,680
     * Total Instances: 10,043,423
     * Classloaders: 5
     * GC Roots: 1,589
     */
//    val regularInstances = List(numberOfElements) { RegularClass(it) }

    // Create instances of the value class
    /**
     * Total Bytes: 202,405,504
     * Total Classes: 1,680
     * Total Instances: 10,043,426
     * Classloaders: 5
     * GC Roots: 1,589
     */
    val valueInstances = List(numberOfElements) { ValueClass(it) }

    createHeapDump()

    // Hold the program to observe memory usage
    println("Press enter to exit...")
}
