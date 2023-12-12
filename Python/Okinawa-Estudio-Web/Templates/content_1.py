import reflex as rx


def content_1():
    return rx.center(
        (
            rx.square(rx.vstack(rx.text("Okinawa"
                                        ,font_family="my-font"
                                        ,color="#FFE1E3"
                                        ,font_size="8vw"
                                        ,padding_right="10%")
                    ,rx.text("Estudio"
                              ,font_family="my-font4"
                              ,color="#FFE1E3"
                              ,font_size="4vw")
                      ,rx.text("Fotografia | Maquillaje | Asesoría"
                              ,font_family="my-font4"
                              ,color="#FFE1E3"
                              ,font_size="2.5vw")
                     )
                    ,width="42%"
                    ,height="86.50vh")  
        )
        ,class_name="content_1",
        padding_top="12vh"
    )    