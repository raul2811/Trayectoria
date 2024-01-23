fn main() {
    let number_64: i64 = 10;//esta es una variable inmutable de tipo i64 osea entero de 64 bits
    // no es posible reasignar el valor de number porque esta no
    // una variable mutable number=2;
    let mut number_32=2; //mut es la palabra reservada para hacer una variable mutable y es un entero de 32 bits
    println!("number_64: El numero es {}, es inmutable y tiene un tamaño de 64 bits.\nnumber_32: El numero es {}, es mutable y tiene un tamaño de 32 bits.", number_64, number_32);
    number_32=3; //reasignamos el valor de number_32
    println!("number_64: El numero es {}, es inmutable y tiene un tamaño de 64 bits.\nnumber_32: El numero es {}, es mutable y tiene un tamaño de 32 bits.", number_64, number_32);
}