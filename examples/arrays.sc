

namespace arrays {
    public func main() {
        var array1 = {1, 2, 3, 4};
        println(array1, array1[1]); //Should print: [address] 2

        var array2 = {1, {2, 3, {4, 5}}};

        println(array2, array2[0], array2[1], array2[1][1], array2[1][2][1]); //Should print: [address] 1 [address] 3 5
    }
}
