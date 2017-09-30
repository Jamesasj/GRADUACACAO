import json, requests
origem = 'Campo Grande cariacica ES'
destino = 'Bento Ferreira vitoria ES'
urlAPI = "http://maps.googleapis.com/maps/api/distancematrix/json?origins=[origem]&destinations=[destino]&mode=driving&language=pt-BR&sensor=false"

urlAPI = urlAPI.replace('[origem]', origem)
urlAPI = urlAPI.replace('[destino]', destino)

response = requests.get(urlAPI)
texto = json.loads(response.content)
linhas = texto['rows']
elements = linhas[0]
elements = elements['elements']
distance = elements[0]
distance = distance['distance']
destination_addresses = texto['destination_addresses']
origin_addresses = texto['origin_addresses']

print ('distancia calculada')
print(distance['value'])
print(distance['text'])
print(destination_addresses[0])
print(origin_addresses[0])
print(str(texto))




