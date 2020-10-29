package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PesawatController {
//    @Qualifier("PesawatServiceImpl")
    @Autowired
    private PesawatService pesawatService;

    @Autowired
    private TipeService tipeService;

    @Autowired
    private TeknisiService teknisiService;

    @Autowired
    private PesawatTeknisiService pesawatTeknisiService;

    @Autowired
    private PenerbanganService penerbanganService;

    @GetMapping("/")
    private String home(){
        return "home";
    }

    @GetMapping("/pesawat")
    private String viewAllPesawat(Model model){
        model.addAttribute("listpesawat",pesawatService.getlistPesawat());
        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/pesawat-tua")
    private String viewAllPesawatTua(Model model){
        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        int year = cal.get(Calendar.YEAR);
//        HashMap<String, List> hashMapHasil = pesawatService.getlistPesawatTua(year);
        List<PesawatModel> listpesawattua = pesawatService.getlistPesawatTua(year);
//        System.out.println(listpesawattua.size());
//        List<String> listusia = hashMapHasil.get("listusia");

        model.addAttribute("listpesawat",listpesawattua);
//        model.addAttribute("listusia", listusia);
        model.addAttribute("year", year);
        return "viewall-pesawat-tua";
    }

    @GetMapping("/pesawat/tambah")
    public String addPesawatFormPage(Model model){
        PesawatModel pesawatModel = new PesawatModel();
        PesawatTeknisiModel pesawatTeknisiModel = new PesawatTeknisiModel();
        List<PesawatTeknisiModel> listpesawatteknisi = new ArrayList<>();
        listpesawatteknisi.add(pesawatTeknisiModel);
        pesawatModel.setListpesawatteknisi(listpesawatteknisi);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipes", tipeService.getlistTipe());
        model.addAttribute("teknisis", teknisiService.getlistTeknisi());
        return "form-tambah-pesawat";
    }

    @PostMapping(path = "/pesawat/", params ="save")
    public String addPesawatSubmit(
            @ModelAttribute PesawatModel pesawatModel,
            Model model
    ){
        String noseri = pesawatService.getnoSeriPesawat(pesawatModel);
        pesawatModel.setNomorSeri(noseri);
        pesawatService.addPesawat(pesawatModel);
        if(pesawatModel.getListpesawatteknisi() != null || pesawatModel.getListpesawatteknisi().size() !=0){
            for(PesawatTeknisiModel pesawatTeknisiModel: pesawatModel.getListpesawatteknisi()){
                pesawatTeknisiModel.setPesawat(pesawatModel);
                pesawatTeknisiService.addPesawatTeknisi(pesawatTeknisiModel);
            }
        }
        model.addAttribute("id", pesawatModel.getNomorSeri());
        return "tambah-pesawat";
    }

    @PostMapping(path="/pesawat/tambah", params={"addItem"})
    public String addItem( @ModelAttribute PesawatModel pesawatModel, Model model) {
        if(pesawatModel.getListpesawatteknisi() == null || pesawatModel.getListpesawatteknisi().size() == 0){
            List<PesawatTeknisiModel> templist = new ArrayList<>();
            pesawatModel.setListpesawatteknisi(templist);
        }
        PesawatTeknisiModel pesawatTeknisiModel = new PesawatTeknisiModel();
        pesawatModel.getListpesawatteknisi().add(pesawatTeknisiModel);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipes", tipeService.getlistTipe());
        model.addAttribute("teknisis", teknisiService.getlistTeknisi());
        return "form-tambah-pesawat";
    }

    @GetMapping("/pesawat/hapus/{idPesawat}")
    private String deletePesawat(@PathVariable Long idPesawat,
                                             Model model) {
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(idPesawat);
        String id = pesawatModel.getNomorSeri();
        pesawatService.deletePesawat(pesawatModel);
        model.addAttribute("id", id);
        return "hapus-pesawat";
    }

    @GetMapping("/pesawat/{id}")
    public String viewPesawat(
            @PathVariable Long id,
            Model model
    ) {
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(id);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("listteknisi", pesawatTeknisiService.getListTeknisibyIdPesawat(pesawatModel));
        return "view-pesawat";
    }

    @GetMapping("/pesawat/{id}/tambah-penerbangan")
    public String tambahPenerbangan(
            @PathVariable Long id,
            Model model
    ) {
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(id);
//        PenerbanganModel penerbanganModel = new PenerbanganModel();
        List<PenerbanganModel> listpenerbangan = new ArrayList<>();
        pesawatModel.setListpenerbangan(listpenerbangan);

        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("listteknisi", pesawatTeknisiService.getListTeknisibyIdPesawat(pesawatModel));
        model.addAttribute("listpenerbangan", penerbanganService.getlistPenerbanganNoPesawat());
        model.addAttribute("listpenerbanganpesawat", pesawatService.getlistPenerbanganPesawat(pesawatModel));
        return "view-pesawat-penerbangan";
    }

    @PostMapping(path = "/pesawat/{id}/tambah-penerbangan", params ="save")
    public String addPenerbanganSubmit(
            @PathVariable Long id,
            @ModelAttribute PenerbanganModel penerbanganModel,
            Model model
    ){
        System.out.println("sfgsdf");
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(id);
        penerbanganService.changePesawatinPenerbangan(pesawatModel);
        List<PenerbanganModel> listpenerbangan = new ArrayList<>();
        pesawatModel.setListpenerbangan(listpenerbangan);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("listteknisi", pesawatTeknisiService.getListTeknisibyIdPesawat(pesawatModel));
        model.addAttribute("listpenerbangan", penerbanganService.getlistPenerbanganNoPesawat());
        model.addAttribute("listpenerbanganpesawat", pesawatService.getlistPenerbanganPesawat(pesawatModel));
        return "view-pesawat-penerbangan";
    }

    @GetMapping("/pesawat/ubah/{idPesawat}")
    public String changePesawatFormPage(
            @PathVariable Long idPesawat,
            Model model
    ){
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(idPesawat);
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipes", tipeService.getlistTipe());
        model.addAttribute("teknisis", teknisiService.getlistTeknisi());
        return "form-ubah-pesawat";
    }

    @PostMapping("/pesawat/ubah")
    public String changePesawatFormSubmit(
            @ModelAttribute PesawatModel pesawatModel,
            Model model
    ){
        PesawatModel updatePesawat = pesawatService.changePesawat(pesawatModel);
        model.addAttribute("id", updatePesawat.getNomorSeri());
        model.addAttribute("tipe", updatePesawat.getTipe());
        return "ubah-pesawat";
    }


//    @GetMapping("/pesawat/filter")
//    public String viewDetailHotel(
//            @RequestParam(value = "idPenerbangan", required = false) Long idPenerbangan,
//            @RequestParam(value = "idTipe", required = false) Long idTipe,
//            @RequestParam(value = "idTeknisi", required = false) Long idTeknisi,
//            Model model
//    ) {
//        if(idPenerbangan == 0){
//            return "error-hotel";
//        }
//    }
}
