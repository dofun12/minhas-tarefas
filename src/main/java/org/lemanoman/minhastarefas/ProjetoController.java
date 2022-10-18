package org.lemanoman.minhastarefas;

import org.lemanoman.minhastarefas.dto.BreadDto;
import org.lemanoman.minhastarefas.dto.ProjetoDto;
import org.lemanoman.minhastarefas.dto.TarefaDto;
import org.lemanoman.minhastarefas.model.ProjetoModel;
import org.lemanoman.minhastarefas.model.TarefaModel;
import org.lemanoman.minhastarefas.service.TarefaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ProjetoController {
    final private TarefaService tarefaService;
    final private SimpleDateFormat inputDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");

    public ProjetoController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    private void setBreacumb(Model model, BreadDto... breads) {
        List<BreadDto> breadlist = new ArrayList<>(Arrays.asList(breads));
        model.addAttribute("breadlist", breadlist);
    }

    @GetMapping({"/", "/projetos/", "/projetos/list"})
    public String getListProjetos(Model model) {
        setBreacumb(model, new BreadDto("Projetos", "/projetos/"));
        model.addAttribute("projetos", tarefaService.getListAllDto());
        return "projetos/list";
    }

    @GetMapping("/projetos/new")
    public String addProjeto(Model model) {
        setBreacumb(model, new BreadDto("Projetos", "/projetos/")
                , new BreadDto("Novo", "/projetos/new")
        );
        ProjetoDto projetoDto = new ProjetoDto();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 7);
        projetoDto.setDataPrazo(inputDateFormat.format(calendar.getTime()));
        model.addAttribute("projeto", projetoDto);
        return "projetos/new";
    }

    @GetMapping("/projetos/{idProjeto}/tarefas/new")
    public String addNovaTarefa(@PathVariable("idProjeto") Integer idProjeto, Model model) {
        ProjetoModel original = tarefaService.getProjetoModel(idProjeto);
        if(original==null){
            return "404";
        }
        setBreacumb(model, new BreadDto("Projetos", "/projetos/")
                , new BreadDto(original.getTitulo(), "/projetos/details/"+idProjeto)
                , new BreadDto("Tarefas", "/projetos/"+idProjeto+"/tarefas/list")
                , new BreadDto("Nova tarefa", "/projetos/"+idProjeto+"/tarefas/new")
        );
        model.addAttribute("tarefa", new TarefaDto());
        return "projetos/tarefas/new";

    }

    @PostMapping("/projetos/{idProjeto}/tarefas/new")
    public String addNovaTarefa(@PathVariable("idProjeto") Integer idProjeto, @ModelAttribute TarefaDto tarefaDto, Model model) {
        ProjetoModel original = tarefaService.getProjetoModel(idProjeto);
        if(original==null){
            return "404";
        }
        TarefaModel tempTarefaModel = tarefaDto.getTarefaModel();
        tarefaService.criarTarefa(original.getIdProjeto(), tempTarefaModel.getTitulo(), tempTarefaModel.getDescricao(), tempTarefaModel.getDataPrazo());
        return "redirect:/projetos/"+idProjeto+"/tarefas/list";

    }

    @GetMapping({"/projetos/{idProjeto}/tarefas/","/projetos/{idProjeto}/tarefas/list" })
    public String listTarefas(@PathVariable("idProjeto") Integer idProjeto, Model model) {
        ProjetoModel projetoModel = tarefaService.getProjetoModel(idProjeto);
        if (projetoModel == null || projetoModel.getIdProjeto() == null) {
            return "404";
        }
        setBreacumb(model, new BreadDto("Projetos", "/projetos/")
                , new BreadDto(projetoModel.getTitulo(), "/projetos/details/"+idProjeto)
                , new BreadDto("Tarefas", "/projetos/"+idProjeto+"/tarefas/list")
        );
        model.addAttribute("projeto", new ProjetoDto(projetoModel));
        model.addAttribute("idProjeto", projetoModel.getIdProjeto());
        model.addAttribute("tarefas", tarefaService.getListTarefasDto(idProjeto));
        return "projetos/tarefas/list";
    }

    @GetMapping("/projetos/details/{idProjeto}")
    public String detailsProjeto(@PathVariable("idProjeto") Integer idProjeto, Model model) {
        ProjetoModel projetoModel = tarefaService.getProjetoModel(idProjeto);
        if (projetoModel == null || projetoModel.getIdProjeto() == null) {
            return "404";
        }
        setBreacumb(model, new BreadDto("Projetos", "/projetos/")
                , new BreadDto(projetoModel.getTitulo(), "/projetos/details/"+idProjeto)
        );
        model.addAttribute("projeto", new ProjetoDto(projetoModel));
        model.addAttribute("idProjeto", projetoModel.getIdProjeto());
        return "projetos/edit";
    }

    @PostMapping("/projetos/details/{idProjeto}")
    public String editProjeto(@PathVariable("idProjeto") Integer idProjeto, @ModelAttribute ProjetoDto projetoDto, Model model) {
        setBreacumb(model, new BreadDto("Projetos", "/projetos/"));
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
        setBreacumb(model, new BreadDto("Projetos", "/projetos/"));
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
