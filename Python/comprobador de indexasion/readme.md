# API para Verificar Indexación en Latindex
Esta API está diseñada para verificar si una revista está indexada en Latindex utilizando su e-ISSN o ISSN. Utiliza la librería requests para hacer solicitudes HTTP y BeautifulSoup para analizar el HTML de la página web de Latindex.

## Requisitos
* Python 3.6 o superior
* Bibliotecas Python: requests, beautifulsoup4, urllib3
## Uso
### Función verificar_indexador_por_eissn
```python
def verificar_indexador_por_eissn(eissn, indexador):
```
* eissn (str): El e-ISSN de la revista que se quiere verificar.
* indexador (str): El indexador a utilizar. En este caso, debe ser 'latinindex'.
La función retorna un mensaje indicando si la revista está indexada en Latindex o no.

### Función verificar_indexador_por_issn
```python
def verificar_indexador_por_issn(issn, indexador):
```

* issn (str): El ISSN de la revista que se quiere verificar.
* indexador (str): El indexador a utilizar. En este caso, debe ser 'latinindex'.
La función retorna un mensaje indicando si la revista está indexada en Latindex o no.

## Ejemplo de Uso
```python
eissn = "2697-3294"
resultado = verificar_indexador_por_eissn(eissn, "latinindex")
print(resultado)
```
Este ejemplo verifica si la revista con e-ISSN 2697-3294 está indexada en Latindex.

### Notas
* Esta API desactiva la verificación del certificado SSL al hacer las solicitudes HTTP. Ten en cuenta que esto puede representar un riesgo de seguridad en un entorno de producción y se recomienda habilitar la verificación SSL adecuada.
* Es importante respetar los términos de uso y las políticas de Latindex al utilizar esta API para evitar problemas legales o de uso indebido.
* planeo ampliar los indexadores disponibles y integrarlo con  fastapi.
