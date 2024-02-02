
import requests,json
from header import headers #** importa la sintaxis de solicud de la api

DATABASE_ID="" # ID de la base de datos que se quiere leer
PAGE_ID='21284938fa4646429d2bb645f2fa18a0' #Page ID de la pagina principal donde esta mario programando

# Response a Database
def responseDatabase(pageID,headers):
    readUrl=f"https://api.notion.com/v1/pages/{pageID}"
    
    res=requests.request("GET",readUrl,headers=headers)
    print(res.status_code)# 200 significa que la peticion fue exitosa , 404 significa que no se encontro la pagina , 401 significa que no se tiene acceso a la pagina , 400 significa que la peticion fue incorrecta
    print(res.text)
responseDatabase(PAGE_ID, headers)