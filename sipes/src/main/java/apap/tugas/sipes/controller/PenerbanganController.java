package apap.tugas.sipes.controller;


import apap.tugas.sipes.model.PenerbanganModel;
import apap.tugas.sipes.model.PesawatModel;
import apap.tugas.sipes.service.PenerbanganService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PenerbanganController {
    @Autowired
    PenerbanganService penerbanganService;

    @GetMapping("/penerbangan")
    private String viewAllPenerbangan(Model model){
        model.addAttribute("listpenerbangan",penerbanganService.getlistPenerbangan());
        return "viewall-penerbangan";
    }
    @GetMapping("/penerbangan/{idPenerbangan}")
    public String viewPesawat(
            @PathVariable Long idPenerbangan,
            Model model
    ) {
        PenerbanganModel penerbanganModel = penerbanganService.getPenerbanganbyid(idPenerbangan);
        model.addAttribute("penerbangan", penerbanganModel);
        return "view-penerbangan";
    }

    @GetMapping("/penerbangan/tambah")
    public String addPenerbanganFormPage(Model model) {
        PenerbanganModel penerbanganModel = new PenerbanganModel();
        model.addAttribute("penerbangan", penerbanganModel);
        return "form-tambah-penerbangan";
    }
    @PostMapping(value = "/penerbangan/tambah", params = {"ubah"})
    public String addPenerbanganSubmit(
            @ModelAttribute PenerbanganModel penerbanganModel,
            Model model
    ){
//        SimpleDateFormat formaDate = new SimpleDateFormat("dd/MM/yyyy");
        penerbanganService.addPenerbangan(penerbanganModel);
        model.addAttribute("id", penerbanganModel.getNomorPenerbangan());
        return "tambah-penerbangan";
    }
    @GetMapping("/penerbangan/hapus/{idPenerbangan}")
    public String deletePenerbanganView(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbanganModel = penerbanganService.getPenerbanganbyid(idPenerbangan);
        String id = penerbanganModel.getNomorPenerbangan();
        model.addAttribute("id", id);
        penerbanganService.deletePenerbangan(penerbanganModel);
        return "hapus-penerbangan";
    }

    @GetMapping("/penerbangan/ubah/{idPenerbangan}")
    public String changePenerbanganFormPage(
            @PathVariable Long idPenerbangan,
            Model model
    ){
        PenerbanganModel penerbanganModel = penerbanganService.getPenerbanganbyid(idPenerbangan);
        model.addAttribute("penerbangan", penerbanganModel);
        return "form-ubah-penerbangan";
    }

    @PostMapping("/penerbangan/ubah")
    public String changePenerbanganFormSubmit(
            @ModelAttribute PenerbanganModel penerbanganModel,
            Model model
    ){
        PenerbanganModel updatePenerbangan = penerbanganService.changePenerbangan(penerbanganModel);
        model.addAttribute("id", updatePenerbangan.getNomorPenerbangan());
        return "ubah-penerbangan";
    }
}
