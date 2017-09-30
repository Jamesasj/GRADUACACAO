select ori.bairro, ori.cidade, des.bairro, des.cidade, dis.distancia, dis.distancia_km from
distancias dis 
inner join enderecos ori on dis.endereco_origem = ori.id_cidade
inner join  enderecos des on des.id_cidade = dis.endereco_destino;

select ori.id_cidade, ori.bairro, ori.cidade, dis.destino_api from
distancias dis 
inner join enderecos ori on dis.endereco_origem = ori.id_cidade
group by  ori.id_cidade,ori.bairro, ori.cidade, dis.destino_api
order by  ori.id_cidade;