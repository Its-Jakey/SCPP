#include <input>

namespace test{
	public func main(){
		var input = input::ask("1/0");
		while (input){
			print("1");
		}
		print("0");
	}
}
