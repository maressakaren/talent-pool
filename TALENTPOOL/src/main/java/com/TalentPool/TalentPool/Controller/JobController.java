package com.TalentPool.TalentPool.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.TalentPool.TalentPool.Model.Candidate;
import com.TalentPool.TalentPool.Model.Job;
import com.TalentPool.TalentPool.Repository.CandidateRepository;
import com.TalentPool.TalentPool.Repository.JobRepository;

import jakarta.validation.Valid;

@Controller

//@RequestMapping("/jobs")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private CandidateRepository candidateRepository;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String form(/*Model model*/) {
        //model.addAttribute("vacancy", new Job());
        return "vaga/formVaga";
    }

    /*
     * Method to create a new candidate
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String form(@Valid Job job, BindingResult result, RedirectAttributes attributes) {
        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verify the fields");
            return "redirect:/create";
        }
        jobRepository.save(job);
        attributes.addFlashAttribute("message", "Job created successfully");
        return "redirect:/create";
    }


    /*
     * Method list all candidates
     */
    @RequestMapping(value = "/jobs", method = RequestMethod.GET)
    public ModelAndView jobList() {
        ModelAndView mv = new ModelAndView("vaga/listaVaga");
        Iterable<Job> jobs = jobRepository.findAll();
        mv.addObject("jobs", jobs);
        return mv;
    }

    /*
     * Method show the details of a Job , by your code
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.GET)
    public ModelAndView jobDetails(@PathVariable long code) {
        Job job = jobRepository.findByCode(code);
        ModelAndView mv = new ModelAndView("job/jobDetails");
        mv.addObject("job", job);

        Iterable<Candidate> candidates = candidateRepository.findByJob(job);
        mv.addObject("candidates", candidates);

        return mv;
    }

    /*
     * Method delete a job by code
     */
    @RequestMapping(value = "/deleteJob")
    public String deleteJob(long code) {
        Job job = jobRepository.findByCode(code);
        jobRepository.delete(job);

        return "redirect:/jobs";
    }

    /*
     * Method update the details of a job
     */
    @RequestMapping(value = "/{code}", method = RequestMethod.POST)
    public String jobDetailsPost(@PathVariable long code,
            @Valid Candidate candidate, BindingResult result, RedirectAttributes attributes) {

        if (result.hasErrors()) {
            attributes.addFlashAttribute("message", "Verify the fields...");
            return "redirect:/{code}";
        }
        /*CONSISTENT TEST*/
        if (candidateRepository.findByRg(candidate.getRg()) != null) {
            attributes.addFlashAttribute("message Error", "Rg duplicate");
            return "redirect:/{code}";
        }

        Job job = jobRepository.findByCode(code);
        candidate.setJob(job);
        candidateRepository.save(candidate);
        attributes.addFlashAttribute("message", "Candidate add succesfully");
        return "redirect:/{code}";

    }

    /*
     * Method delete a candidate ( I need change that later)
     */
    @RequestMapping(value = "/deleteCandidate")
    public String deleteCandidate(String rg) {
        Candidate candidate = candidateRepository.findByRg(rg);
        Job job = (Job) candidate.getJob();
        String code = "" + job.getCode();
        candidateRepository.delete(candidate);
        return "redirect:/" + code;

    }

    /*
     * This following Methods update a job
     */
    @RequestMapping(value = "/edit-job", method = RequestMethod.GET)
    public ModelAndView editJob(long code) {
        Job job = jobRepository.findByCode(code);
        ModelAndView mv = new ModelAndView("job/update-job");
        mv.addObject("job", job);
        return mv;
    }

    @RequestMapping(value = "/edit-job", method = RequestMethod.POST)
    public String updateJob(@Valid Job job, BindingResult result, RedirectAttributes attributes) {
        jobRepository.save(job);
        attributes.addFlashAttribute("sucsess", "Job update with sucess");
        long codeLong = job.getCode();
        String code = "" + codeLong;
        return "redirect/:" + code;
    }

}
