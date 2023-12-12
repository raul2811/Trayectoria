import reflex as rx

def section_1():#declaramos la funcion
    return rx.box(#la caja que envuelve todo
    rx.box(#lo ordena todo de manera vertical
        rx.image(src="section_1__1.webp"
                 ,width="50%"
                 ,height="100%"
                 ,float="right")
        ,rx.box(
            rx.text("写真術"
            ,font_family="my-font2"
            ,color="#F50F0F"
            ,font_size="8vw"
            ,writing_mode= "vertical-rl"
            ,text_orientation= "upright"
            ,text_shadow="2px 5px 4px rgba(0,0,0,1)"
        )
        ,position="absolute"
        ,padding_left="44%"
        ,padding_top="15%"
        )
)   
    ,rx.heading("Quienes Somos"
                ,id="Quienes Somos"
                ,width="45%"
                ,padding_top="6%"
                ,top="0px"
                ,font_family="my-font2"
                ,color="#F50F0F"
                ,margin_left="2%"
                ,font_size="3.5vw"
                 
                )#titulo de la seccion 1 de la pagina
    ,rx.box(rx.text("Okinawa Estudio, tu destino fotográfico en el corazón de Panamá, fusiona la elegancia de la cultura japonesa con la calidez y la vitalidad del entorno panameño."
                    ,font_size="2vw")
            ,rx.text("Nuestro estudio se erige como un oasis creativo donde la estética japonesa se entrelaza con la rica diversidad de Panamá, ofreciendo una experiencia única para tus sesiones fotográficas."
                     ,padding_top="3%"
                     ,font_size="2vw"
                     )
            ,border_top="2px solid"
            ,border_color="#F50F0F"
            ,width="35%"    
            ,margin_left="5%"
            ,margin_top="5%"
            ,padding_top="3%"
            )
    #,margin_top="25.60%"#para geneerar espacio en entre el index y la section 1
    ,class_name="Section_1"#nombre de la clase que envuelve todo 
)

