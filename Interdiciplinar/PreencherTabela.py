import json, requests, psycopg2
from datetime import datetime

def obterDistancia(origem, destino):
    urlAPI = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=[origem]&destinations=[destino]&mode=driving&language=pt-BR&sensor=false"
    urlAPI = urlAPI.replace('[origem]', origem)
    urlAPI = urlAPI.replace('[destino]', destino)
    response = requests.get(urlAPI)
    texto = json.loads(response.content)
    destination_addresses = texto['destination_addresses']
    origin_addresses = texto['origin_addresses']
    linhas = texto['rows']
    elements = linhas[0]
    elements = elements['elements']
    distance = elements[0]
    distance = distance['distance']
    return [distance['value'], distance['text'], destination_addresses[0], origin_addresses[0], str(texto).replace('\'','') ]

GOOGLE_API = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=[origem]&destinations=[destino]&mode=driving&language=pt-BR&sensor=false"
CON = psycopg2.connect(host='localhost', database='faesa', user='postgres', password='j753294s')
SQL_INSERIR = 'INSERT INTO public.distancias(endereco_origem, endereco_destino, distancia, distancia_km, origem_api, destino_api, resposta_api) VALUES (?, ?, ?, \'?\', \'?\', \'?\', \'?\');'
SQL_SELECAO1 = 'SELECT id_cidade, bairro|| \' \' ||cidade|| \' ES\' loc FROM public.enderecos where id_cidade >= 108;'
SQL_SELECAO2 = 'SELECT id_cidade, bairro|| \' \' ||cidade|| \' ES\' loc  FROM public.enderecos WHERE id_cidade <> ?'
COUNTER = 11771

now = datetime.now()
print(now)

cursor = CON.cursor()
cursor.execute(SQL_SELECAO1)
tblEndOrigens = cursor.fetchall()
cursor.close()

for origem in tblEndOrigens:
    cursor = CON.cursor()
    sqlDestinos = SQL_SELECAO2.replace('?',str(origem[0]),1)
    cursor.execute(sqlDestinos)
    tblEndeDestinos = cursor.fetchall()
    cursor.close()

    for destino in tblEndeDestinos:
        distancia = obterDistancia(str(origem[1]), str(destino[1]))
        sqlResult = SQL_INSERIR.replace('?', str(origem[0]),1)
        sqlResult = sqlResult.replace('?', str(destino[0]),1)
        sqlResult = sqlResult.replace('?', str(distancia[0]),1)
        sqlResult = sqlResult.replace('?', str(distancia[1]),1)
        sqlResult = sqlResult.replace('?', str(distancia[2]),1)
        sqlResult = sqlResult.replace('?', str(distancia[3]),1)
        sqlResult = sqlResult.replace('?', str(distancia[4]),1)
        cursor = CON.cursor()
        cursor.execute(sqlResult)
        CON.commit()
        cursor.close()
        COUNTER += 1
        print(COUNTER)

now = datetime.now()
print(now)
print('CONCLUIDO')