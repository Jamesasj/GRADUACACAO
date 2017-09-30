import psycopg2
con = psycopg2.connect(host='localhost', database='faesa', user='postgres', password='j753294s')


sql = 'SELECT * FROM public.enderecos;'

cursor = con.cursor()
cursor.execute(sql)

recset = cursor.fetchall()
for rec in recset:
  print (rec)

con.close()