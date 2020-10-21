package apap.tugas.sipes.service;


import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.TipeModel;
import apap.tugas.sipes.repository.PesawatDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
public class PesawatServiceImpl implements PesawatService {

    @Autowired
    PesawatDb pesawatDb;

    @Override
    public List<PesawatModel> getlistPesawat() {
        return pesawatDb.findAll();
    }

    @Override
    public void addPesawat(PesawatModel pesawatModel) {
        pesawatDb.save(pesawatModel);
    }

    @Override
    public String getNameTipe(PesawatModel pesawatModel) {
        String tipeModel = pesawatModel.getTipe().getNama();
        return tipeModel;
    }

    @Override
    public PesawatModel getPesawatbyid(Long id) {
        return pesawatDb.findById(id).get();
    }

    @Override
    public List<PesawatModel> getlistPesawatTua(int year) {
        List<PesawatModel> listpesawat = pesawatDb.findAll();
        List<PesawatModel> listpesawatTua = new ArrayList<>();
        List<String> listusia = new ArrayList<>();
        for(PesawatModel pesawat:listpesawat){
            Date date = pesawat.getTanggalDibuat();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year_pesawat = cal.get(Calendar.YEAR);
            int usia = year - year_pesawat;
//            System.out.println(usia);
            if(usia > 10){
                listpesawatTua.add(pesawat);
                listusia.add(Integer.toString(usia));
            }
            System.out.println(listpesawatTua.size());

        }
//        HashMap<String, List> hashHasil = new HashMap<>();
//        hashHasil.put("listpesawattua", listpesawat);
//        hashHasil.put("listusia", listusia);
        return listpesawatTua;
    }

    private int temp_alfa1;
    private int temp_alfa2;

    @Override
    public String getnoSeriPesawat(PesawatModel pesawatModel) {
        String noseri = "";

        // jenis pesawat
        if(pesawatModel.getJenisPesawat().equalsIgnoreCase("Komersil")){
            noseri = noseri.concat("1");
        }
        else {
            noseri = noseri.concat("2");
        }


        // tipe pesawat
        if(pesawatModel.getTipe().getNama().equalsIgnoreCase("Boeing")){
            noseri = noseri.concat("BO");
        }
        else if(pesawatModel.getTipe().getNama().equalsIgnoreCase("ATR")){
            noseri = noseri.concat("AT");
        }
        else if(pesawatModel.getTipe().getNama().equalsIgnoreCase("Airbus")){
            noseri = noseri.concat("AB");
        }
        else {
            noseri = noseri.concat("BB");
        }

        //  tahun pesawat
        Date date = pesawatModel.getTanggalDibuat();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        String year_string = Integer.toString(year);
        String year_final = new StringBuilder(year_string).reverse().toString();
        noseri = noseri.concat(year_final);

        // tahun pesawat tambah 8
        int year_8 = year + 8;
        noseri = noseri.concat(Integer.toString(year_8));

        // random
        noseri = noseri.concat("AA");
        while (pesawatDb.findByNomorSeri(noseri).isPresent()) {
            int lowerLimit = 65;
            int upperLimit = 90;
            Random random = new Random();
            StringBuffer r = new StringBuffer(2);

            for (int i = 0; i < 2; i++) {

                // take a random value between 97 and 122
                int nextRandomChar = lowerLimit
                        + (int) (random.nextFloat()
                        * (upperLimit - lowerLimit + 1));

                // append a character at the end of bs
                r.append((char) nextRandomChar);
            }
            String rand = noseri.substring(0, 11);
            noseri = rand.concat(String.valueOf(r));
        }

        return noseri;
    }

    @Override
    public void deletePesawat(PesawatModel pesawatModel) {
        pesawatDb.delete(pesawatModel);
    }

    @Override
    public PesawatModel changePesawat(PesawatModel pesawatModel) {
        PesawatModel targetPesawat = pesawatDb.findById(pesawatModel.getId()).get();
        try{
            boolean temp = false;
            targetPesawat.setMaskapai(pesawatModel.getMaskapai());
            targetPesawat.setTempatDibuat(pesawatModel.getTempatDibuat());
            if(!targetPesawat.getTanggalDibuat().equals(pesawatModel.getTanggalDibuat())) {
                targetPesawat.setTanggalDibuat(pesawatModel.getTanggalDibuat());
                temp = true;
            }
            if(!targetPesawat.getJenisPesawat().equals(pesawatModel.getJenisPesawat())) {
                targetPesawat.setJenisPesawat(pesawatModel.getJenisPesawat());
                temp = true;
            }
            if(temp){
                String new_noSeri = getnoSeriPesawat(targetPesawat);
                targetPesawat.setNomorSeri(new_noSeri);
            }
            pesawatDb.save(targetPesawat);
            return targetPesawat;
        } catch (NullPointerException nullException){
            return null;
        }
    }
}
