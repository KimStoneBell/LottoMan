package example.dagger.coffee.struct.cafe

class CafeInfo(val name : String = ""){
    fun welcome(){
        System.out.println("Welcome $name")
    }
}