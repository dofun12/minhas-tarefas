package org.lemanoman.minhastarefas;

import org.lemanoman.minhastarefas.dto.ProjetoDto;
import org.lemanoman.minhastarefas.model.ProjetoModel;
import org.lemanoman.minhastarefas.service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.websocket.server.PathParam;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
public class ProjetoController {
    final private TarefaService tarefaService;
    final private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public ProjetoController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    @GetMapping({"/", "/projetos/", "/projetos/list"})
    public String getListProjetos(Model model) {
        model.addAttribute("projetos", tarefaService.getListAllDto());
        return "projetos/list";
    }

    @GetMapping("/projetos/new")
    public String addProjeto(Model model) {
        ProjetoDto projetoDto = new ProjetoDto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        projetoDto.setDataPrazo(inputDateFormat.format(calendar.getTime()));
        model.addAttribute("projeto", projetoDto);
        return "projetos/new";
    }

    @GetMapping("/projetos/details/{idProjeto}")
    public String detailsProjeto(@PathVariable("idProjeto") Integer idProjeto, Model model) {
        ProjetoModel projetoModel = tarefaService.getProjetoModel(idProjeto);
        if (projetoModel == null || projetoModel.getIdProjeto() == null) {
            return "404";
        }
        model.addAttribute("projeto", new ProjetoDto(projetoModel));
        model.addAttribute("idProjeto", projetoModel.getIdProjeto());
        return "projetos/edit";
    }

    @PostMapping("/projetos/details/{idProjeto}")
    public String editProjeto(@PathVariable("idProjeto") Integer idProjeto, @ModelAttribute ProjetoDto projetoDto, Model model) {
        ProjetoModel original = tarefaService.getProjetoModel(idProjeto);
        if(original==null){
            return "404";
        }
        ProjetoDto originalDto = new ProjetoDto(original);
        originalDto.setDataPrazo(projetoDto.getDataPrazo());
        originalDto.setTitulo(projetoDto.getTitulo());
        originalDto.setDescricao(projetoDto.getDescricao());
        tarefaService.saveProjeto(originalDto.getProjetoModel());
        return "redirect:/projetos/list";

    }

    @PostMapping("/projetos/new")
    public String postProjeto(@ModelAttribute ProjetoDto projetoDto, Model model) {
        model.addAttribute("projeto", projetoDto);
        Date dataPrazo = null;
        try {
            dataPrazo = inputDateFormat.parse(projetoDto.getDataPrazo());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tarefaService.criarProjeto(projetoDto.getTitulo(), projetoDto.getDescricao(), dataPrazo);
        return "redirect:/projetos/list";


    }
}
