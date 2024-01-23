
struct User{username:String,email:String,sign_in_count:u64,active:bool,}
struct Color(String,String,String);
 fn  main(){
    //------------------------Tuplas------------------------
    println!("------------------------Tuplas------------------------");
    let tuple_e=('E',5i32,true);
    println!("tuple_e:\n1:{} \n2:{}\n3:{}",tuple_e.0, tuple_e.1, tuple_e.2);
    //------------------------Estructuras------------------------
    println!("------------------------Estructura clasica------------------------");
    let user1=User{username: String::from("Raul"),email: String::from("raulantoni2810@gmail.com"),sign_in_count:2,active:true};
    println!("Usuario1:\nusername:{}\nemail:{}\nsign in:{}\nactive:{}",user1.username,user1.email,user1.sign_in_count,user1.active);
    println!("------------------------Estructura Tupla------------------------");
    let color1=Color(String::from("red"),String::from("blue"),String::from("green"));
    println!("Color 1:\n1:{}\n2:{}\n3:{}",color1.0,color1.1,color1.2);
 }