import reflex as rx

def navbar():#declaramos la funcion
    return rx.box(#la caja que envuelve todo
        rx.hstack(#ordena todo de manera horizontal
            rx.hstack(#ordena todo de manera horizontal
                rx.link(rx.image(src="/facebook-f.svg"
                                 ,width="1em")
                                    ,href=""),#enlaces dela barra de navegacion
                rx.link(rx.image(src="/instagram-logo.svg"
                                 ,width="1.5em")
                                    ,href=""),#enlaces dela barra de navegacion
                rx.link(rx.image(src="/whatsapp.svg",width="1.5em")),spacing="7",padding_left="2%",padding_top="1.5%",href=""),#enlaces dela barra de navegacion
            rx.link("Quienes Somos",padding_left="7%",padding_top="1.5%",color="#FFE1E3",font_family="my-font4",font_size="125%",href="#Quienes Somos"),#enlaces dela barra de navegacion
            rx.link("Portafolio",padding_left="4%",padding_top="1.5%",color="#FFE1E3",font_family="my-font4",font_size="125%",href=""),#enlaces dela barra de navegacion
            rx.link("Contacto",padding_left="4%",padding_top="1.5%",color="#FFE1E3",font_family="my-font4",font_size="125%",href=""),#enlaces dela barra de navegacion
            rx.spacer(),
            rx.link("Okinawa Estudio",font_family="my-font4",color="#FFE1E3",font_size="150%",padding_right="2%",padding_top="1.5%",href="/"),
        ),
        position="fixed",#mantiene la barra de navegacion siempre arriba
        padding_bottom="2%",#pequeño espaciado
        width="100%",#para que tome el 100% del ancho de la pantalla  del dispositivo
        top="0px",#para mantenerlo siempre arriba
        z_index="5",#para mantenerlo siempre arriba
        bg="#000000",#color de fondo
        box_shadow="0px 7px 0px 0px rgba(0,0,0,0.24)"#la sombra que se le ve abajo a la barra de navegacion con una pequeña transparencia
    )

    
