import psycopg2
con = psycopg2.connect(host='localhost', database='faesa', user='postgres', password='j753294s')


sql = 'INSERT INTO public.enderecos(bairro, cidade, id_cidade)VALUES (?, ?, ?);'

sql = sql.replace('?','\'Centro\'',1)
sql = sql.replace('?','\'Vitoria\'',1)
sql = sql.replace('?','3' ,1)

cursor = con.cursor()

cursor.execute(sql)
con.commit()
con.close()