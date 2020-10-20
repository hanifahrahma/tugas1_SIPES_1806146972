package apap.tugas.sipes.controller;

import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.model.PesawatTeknisiModel;
import apap.tugas.sipes.repository.PesawatTeknisiDb;
import apap.tugas.sipes.service.PesawatService;
import apap.tugas.sipes.service.PesawatTeknisiService;
import apap.tugas.sipes.service.TeknisiService;
import apap.tugas.sipes.service.TipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

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
        model.addAttribute("listpesawat",pesawatService.getlistPesawat());
        return "viewall-pesawat";
    }

    @GetMapping("/pesawat/tambah")
    public String addHotelFormPage(Model model){
        PesawatModel pesawatModel = new PesawatModel();
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("tipes", tipeService.getlistTipe());
        PesawatTeknisiModel pesawatTeknisiModel = new PesawatTeknisiModel();
        pesawatTeknisiModel.setPesawat(pesawatModel);
        model.addAttribute("pesawatteknisi", pesawatTeknisiModel);
        model.addAttribute("teknisis", teknisiService.getlistTeknisi());
        return "form-tambah-pesawat";
    }

    @PostMapping("/pesawat/tambah")
    public String addHotelSubmit(
            @ModelAttribute PesawatModel pesawatModel,
            Model model
    ){
//        SimpleDateFormat formaDate = new SimpleDateFormat("dd/MM/yyyy");

        pesawatService.addPesawat(pesawatModel);
        model.addAttribute("id", pesawatModel.getId());
        return "tambah-pesawat";
    }

    @GetMapping("/pesawat/{id}")
    public String viewPesawat(
            @PathVariable Long id,
            Model model
    ) {
        PesawatModel pesawatModel = pesawatService.getPesawatbyid(id).get();
        model.addAttribute("pesawat", pesawatModel);
        model.addAttribute("listteknisi", pesawatTeknisiService.getListTeknisibyIdPesawat(pesawatModel));
        return "view-pesawat";
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
