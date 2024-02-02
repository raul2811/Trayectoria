
import requests,json,time
from header import headers , page_ids,database_ids #** importa la sintaxis de solicitud de la API

#ejemplo de solicitud de la API
def readDatabase(databaseID, headers):
    readUrl = f"https://api.notion.com/v1/databases/{databaseID}/query"
    res = requests.request("POST", readUrl, headers=headers)
    data = res.json()
    print(res.status_code)
    print(res.text)
    with open('./full-properties.json', 'w', encoding='utf8') as f:
        json.dump(data, f, ensure_ascii=False)
    return data