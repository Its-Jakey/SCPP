#include "JSONParser.sc"
#include <cloud>
#include <strings>
#include <math>
#include <input>

public namespace rest {
    var clientID = "28";
    var serverID = "29";

    public func send(msg) {
        var rawData = cloud::compress(cloud::encode(msg), "99", 2, 2);
        var piece_n = math::ceil(strings::sizeOf(rawData) / 256);
        piece_n = strings::repeat("0", 2 - strings::sizeOf(piece_n));

        var peices = malloc(piece_n);
        var len = strings::sizeOf(rawData);

        for (i from 0 to piece_n - 1)
            peices[i] = strings::substring(rawData, i * 256, math::min((i + 1) * 256, len));

        var status = cloud::getVar(0);
        
        while (strings::charAt(status, 0) != 0)
            status = cloud::getVar(0);
        while (1) {
            for (i from 0 to piece_n)
                cloud::setVar(i + 1, peices[i]);
            cloud::setVar(0, "100"..piece_n..clientID);

            while (strings::charAt(status, 0) == 0)
                status = cloud::getVar(0);
            if (strings::charAt(status, 1) == 0)
                return;
            println("Data failed to send, retrying...");
        }
    }
    public func waitAndReceive() {
        var status = cloud::getVar(0);
        
        while (((strings::charAt(status, 0) == 1) && (strings::charAt(status, 6) == strings::charAt(serverID, 0)) && (strings::charAt(status, 7) == strings::charAt(serverID, 1))) < 1)
            status = cloud::getVar(0);
        var piece_n = strings::substring(status, 3, 5);
        
        var rawData = "";
        for (i from 0 to piece_n)
            rawData ..= cloud::getVar(i + 1);
        cloud::setVar(0, "0000000");

        return cloud::decode(cloud::decompress(rawData, "99", 2, 2));
    }
}

namespace main {
    public func main() {
        var json = readFromLocalFile("test.json");
        JSONLexer::lex(json);

        JSONObject::unpack(JSONParser::parse());
        println(JSONObject::get("testLabel")[1]);

        while (1)
            rest::send(input::ask("Message to send"));
    }
}