#include <cloud>
#include <input>


namespace cloudCompressor {
    public func main() {
        var inputMsg = input::ask("Input");
        println("Input message: ", inputMsg);

        var encodedInput = cloud::encode(inputMsg);
        println("Encoded message: ", encodedInput);

        var compressedInput = cloud::compress(encodedInput, 99);
        println("Compressed message: ", compressedInput);

        var decompressedInput = cloud::decompress(compressedInput, 99);
        println("Decompressed message: ", decompressedInput);

        var decodedInput = cloud::decode(decompressedInput);
        println("Output: ", decodedInput);
    }
}