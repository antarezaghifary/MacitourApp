package smktelkom_mlg.macitourapp.model;

/**
 * Created by MirzaUY on 2/14/2018.
 */

public class Wisata {
    private String id;
    private String nama;
    private String deskripsi;
    private String alamat;
    private String foto;
    private String fasilitas;
    private String info_tiket;
    private String info_trans;
    private String kearifan;
    private String koordinat;
    private String rate;
    //private String comment;

    public Wisata() {
    }

    public Wisata(String id, String nama, String deskripsi, String alamat, String foto, String fasilitas,
                  String info_tiket, String info_trans, String kearifan, String koordinat, String rate) {
        this.id = id;
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.alamat = alamat;
        this.foto = foto;
        this.fasilitas = fasilitas;
        this.info_tiket = info_tiket;
        this.info_trans = info_trans;
        this.kearifan = kearifan;
        this.koordinat = koordinat;
        this.rate = rate;
        //this.comment = comment;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getFasilitas() {
        return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
        this.fasilitas = fasilitas;
    }

    public String getInfo_tiket() {
        return info_tiket;
    }

    public void setInfo_tiket(String info_tiket) {
        this.info_tiket = info_tiket;
    }

    public String getInfo_trans() {
        return info_trans;
    }

    public void setInfo_trans(String info_trans) {
        this.info_trans = info_trans;
    }

    public String getKearifan() {
        return kearifan;
    }

    public void setKearifan(String kearifan) {
        this.kearifan = kearifan;
    }

    public String getKoordinat() {
        return koordinat;
    }

    public void setKoordinat(String koordinat) {
        this.koordinat = koordinat;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

   /* public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }*/




}
