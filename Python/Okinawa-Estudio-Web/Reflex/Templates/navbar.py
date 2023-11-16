import reflex as rx


def navbar():
    return rx.box(
        rx.hstack(
            rx.link("Okinawa Estudio",font_family="my-font4",color="#FFE1E3",font_size="150%",padding_left="3%"),
            rx.spacer(),
            rx.link("Portafolio",padding_left="3%",padding_top="0.8%",color="#FFE1E3",font_family="my-font4",font_size="100%"),
            rx.link("Acerca de",padding_left="3%",padding_top="0.8%",color="#FFE1E3",font_family="my-font4",font_size="100%"),
            rx.link("Contacto",padding_left="3%",padding_top="0.8%",color="#FFE1E3",font_family="my-font4",font_size="100%"),
            rx.link("f",padding_left="3%",padding_right="5%",padding_top="0.8%",color="#FFE1E3",font_family="facebook",font_size="100%"),
        ),
        position="fixed",
        padding_bottom="1%",
        width="100%",
        top="0px",
        z_index="5",
        bg="#6E593C"
    )
    
