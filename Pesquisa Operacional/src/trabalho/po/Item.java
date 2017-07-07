package trabalho.po;

class Item {

    private String chaveTerciaria;
    private long chaveSecundaria;
    private String chavePrimaria;

    public Item(String _chavePrim, String _chaveTerc, long _chaveSec) {
        this.chavePrimaria = _chavePrim;
        this.chaveSecundaria = _chaveSec;
        this.chaveTerciaria = _chaveTerc;
    }

    public String getChavePrim() {
        return this.chavePrimaria;
    }

    public long getChaveSec() {
        return this.chaveSecundaria;
    }

    public String getChaveTerc() {
        return this.chaveTerciaria;
    }

    public void setChavePrim(String _chavePrim) {
        this.chavePrimaria = _chavePrim;
    }

    public void setChaveSec(long _chaveSec) {
        this.chaveSecundaria = _chaveSec;
    }

    public void setChaveTerc(String _chaveTerc) {
        this.chaveTerciaria = _chaveTerc;
    }

    public String toString() {
        return this.chavePrimaria + ";" + this.chaveSecundaria + ";" + this.chaveTerciaria;
    }

    //Compara com o _item, pra ver se o (this.) � menor, igual ou maior que o _item; 
    public int compareTo(Item _item) {
        Item aux = new Item(this.chavePrimaria, this.chaveTerciaria, this.chaveSecundaria);

        if (Item.compareTo(aux, _item) == -1) {
            return -1;
        } else {
            if (Item.compareTo(aux, _item) == 0) {
                if (this.getChaveSec() < _item.getChaveSec()) {
                    return -1;
                } else {
                    if (this.getChaveSec() == _item.getChaveSec()) {
                        if (this.getChavePrim().compareTo(_item.getChavePrim()) < 0) {
                            return -1;
                        } else {
                            if (this.getChavePrim().compareTo(_item.getChavePrim()) == 0) {
                                return 0;
                            }
                        }
                    }
                }
            }
        }
        return 1;
    }

    //===========================Metodos Staticos============================
    //Compara Letra por letra do item1 em rela��o ao item2, e retorna (1 para >), (0 para ==) e (-1 para <).
    public static int compareTo(Item item1, Item item2) {
        int i, tam1 = item1.getChaveTerc().length(), tam2 = item2.getChaveTerc().length();

        for (i = 0; i < tam1 && i < tam2; i++) {
            if (item1.getChaveTerc().charAt(i) < item2.getChaveTerc().charAt(i)) {
                return -1;
            } else {
                if (item1.getChaveTerc().charAt(i) > item2.getChaveTerc().charAt(i)) {
                    return 1;
                }
            }
        }

        if (tam1 != tam2) {
            if (tam1 > tam2) {
                return 1;
            } else {
                return -1;
            }
        } else {
            return 0;
        }
    }
}