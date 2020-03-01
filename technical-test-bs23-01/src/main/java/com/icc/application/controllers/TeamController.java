package com.icc.application.controllers;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.icc.application.dto.PlayerDto;
import com.icc.application.dto.TeamDto;
import com.icc.application.exceptions.ResourceAlreadyExistsException;
import com.icc.application.model.Country;
import com.icc.application.model.Team;
import com.icc.application.service.AuthorityService;
import com.icc.application.service.CountryService;
import com.icc.application.service.TeamService;
import com.icc.application.service.UserService;
import com.icc.application.util.Constants;

import antlr.collections.List;

@Controller
public class TeamController {

	@Autowired
	TeamService teamService;
	@Autowired
	CountryService countryService;
	@Autowired	ServletContext context;
	@Autowired
	UserService userService;
	
	@Autowired
	AuthorityService authorityService;
	
	
	

	@GetMapping("team/add")
	public String getAddTeamPage(Model model) {
		model.addAttribute("pageTitle", "Add team");
		model.addAttribute("team", new TeamDto());
		var cuntries= countryService.getAllCountries("",0,100,"NA").getContent();
		 Map< Long, String > Countries = new HashMap<Long, String>();	          
	        for(Country item : cuntries){
	        	Countries.put(item.getId(), item.getCountryName());	
	    	}		
		model.addAttribute("countryList", Countries);		
		model.addAttribute("message", "Add a new team");
		return "team/add";
	}

	@PostMapping("/team/add")
	public String addTeam(@ModelAttribute(name = "team") TeamDto team,@RequestParam("file") MultipartFile file,Model model) {
		if(team.getName() == null || team.getName().trim().isEmpty()) {
			throw new RuntimeException("Please give team name");
		}
		if(team.getType() == null || team.getType().trim().isEmpty()) {
			throw new RuntimeException("Please give team  type");
		}		
		if (file.isEmpty()) {
			 throw new RuntimeException("Please select a file to upload");
		}
		 try {
			 byte[] bytes = file.getBytes();
			 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
			 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
	         Files.write(path, bytes);
	         team.setLogo(file.getOriginalFilename());
	         teamService.insert(team);model.addAttribute("message", "Team added successfully");
	         return "redirect:/team/show-all?_search=&_pageIndex=0&_rows=5&_sort=NA";
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }	
		
	}


	@RequestMapping(value = "team/show-all",params= {"_search","_pageIndex","_rows","_sort"},  method = RequestMethod.GET)
	public String showAll(Model model,
			@RequestParam(value = "_search") String searchText,
			@RequestParam(value = "_pageIndex") int pageIndex,
			@RequestParam(value = "_rows") int rows,
			@RequestParam(value = "_sort") String sort) {
		 
		var teamPage=teamService.getAllTeams(searchText,pageIndex,rows,sort);		 
		model.addAttribute("pageTitle", "team List");
		model.addAttribute("teams",teamPage.getContent());
		model.addAttribute("message", "Showing all teams...");
		model.addAttribute("team", new Country());
		model.addAttribute("totalPages",teamPage.getTotalPages());
		model.addAttribute("pageIndex",pageIndex);
		return "/team/show-all";
	}

	@GetMapping("/team/edit")
	public String editTeam(Model model, @RequestParam("id") long id) {
		var cuntries= countryService.getAllCountries("",0,100,"NA").getContent();
		 Map< Long, String > Countries = new HashMap<Long, String>();	          
	        for(Country item : cuntries){
	        	Countries.put(item.getId(), item.getCountryName());	
	    	}		
		model.addAttribute("countryList", Countries);	
		Team team = teamService.getTeamByTeamId(id);
		TeamDto teamdto= new TeamDto();
		teamdto.setId(team.getId());
		teamdto.setCountry(team.getCountry());
		teamdto.setCountryId(team.getCountry().getId());
		teamdto.setName(team.getName());
		teamdto.setTeamDescription(team.getTeamDescription());
		teamdto.setType(team.getType());
		teamdto.setLogo(team.getLogo());
		model.addAttribute("team", teamdto);				
		return "team/edit";
	}

	@PostMapping("/team/editTeam")
	public String saveEditedTeam(@ModelAttribute(name = "team") TeamDto team,@RequestParam(name="file",required=false)  MultipartFile file,Model model) {
		
		if(team.getName() == null || team.getName().trim().isEmpty()) {
			throw new RuntimeException("Please give team name");
		}
		if(team.getType() == null || team.getType().trim().isEmpty()) {
			throw new RuntimeException("Please give team  type");
		}
		var filePath="";
		if(team.getLogo()==null || team.getLogo().isEmpty()) {
			if (file.isEmpty()) {
				 throw new RuntimeException("Please select a file to upload");
			}
			try {
				 byte[] bytes = file.getBytes();
				 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
				 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
		         Files.write(path, bytes);
		         team.setLogo(file.getOriginalFilename());
		         teamService.update(team);model.addAttribute("message", "Team saved successfully");
		         return "redirect:/team/show-all?_search=&_pageIndex=0&_rows=5&_sort=NA";
		    }catch (IOException e) {
		        	throw new RuntimeException(e.getMessage());
		    }
		}else {
			teamService.update(team);model.addAttribute("message", "Team saved successfully");		
		}	
		
		return "redirect:/team/show-all?_search=&_pageIndex=0&_rows=5&_sort=NA";
	}
	
	@GetMapping("/team/detail")
	public String detailTeam(Model model, @RequestParam("id") long id) {
		var team= teamService.getTeamByTeamId(id);
		model.addAttribute("team",team);
		model.addAttribute("members", team.getMembers());
		model.addAttribute("countryList", countryService.getAllCountries("",0,100,"NA").getContent());		
		return "team/detail";
	}
	
	@GetMapping("/team/{id}/add/player")
	public String addPlayerTeam(Model model, @PathVariable(value="id") long id) {
		model.addAttribute("team", teamService.getTeamByTeamId(id));
		model.addAttribute("player", new PlayerDto());		
		model.addAttribute("countryList", countryService.getAllCountries("",0,100,"NA").getContent());		
		return "team/add-player";
	}
	@PostMapping("/team/{id}/add/player")
	public String addPlayerTeam(@ModelAttribute(name = "player") PlayerDto playerDto,@RequestParam("file") MultipartFile file,Model model) {
		if(playerDto.getName() == null || playerDto.getName().trim().isEmpty()) {
			throw new RuntimeException("Please give  name");
		}
		if(playerDto.getPassword() == null || playerDto.getPassword().trim().isEmpty()) {
			throw new RuntimeException("Please give   password.");
		}		
		if (file.isEmpty()) {
			 throw new RuntimeException("Please select a file to upload");
		}
		 try {
			 byte[] bytes = file.getBytes();
			 String absoluteFilePath = context.getRealPath(Constants.UPLOADED_FOLDER);
			 Path path = Paths.get(absoluteFilePath + file.getOriginalFilename());
	         Files.write(path, bytes);
	         playerDto.setLogo(file.getOriginalFilename());	         
	         teamService.insertPlayer(playerDto);model.addAttribute("message", "Player added successfully");
	         return "redirect:/team/detail?id="+playerDto.getTeamId();
	    }catch (IOException e) {
	        	throw new RuntimeException(e.getMessage());
	    }		
	}	
	@GetMapping("/team/{teamId}/delete/player")
	public String deletePlayerByTeamIdAndPlayerId(Model model, @PathVariable("teamId") long teamId,@RequestParam("playerId") long playerId) {
		
		var team= teamService.getTeamByTeamId(teamId);		
		team.getMembers().removeIf(p->p.getUserId()==playerId);
		teamService.updateTeamMember(team);
		model.addAttribute("message", "team member deleted successfully");
		return "redirect:/team/detail?id="+teamId;
	}
	
	@GetMapping("/team/delete")
	public String deleteTeamByTeamId(Model model, @RequestParam("id") long id) {
		teamService.deleteById(id);
		model.addAttribute("message", "team deleted successfully");
		return "redirect:/team/teams";
	}
	
	@GetMapping("/team/deleteImage")
	public String deleteTeamImageByTeamId(Model model,@RequestParam("logo") String logo, @RequestParam("id") long id) {
		teamService.deleteById(id);
		model.addAttribute("message", "team deleted successfully");
		return "redirect:/team/teams";
	}	
	@GetMapping("/team/{id}/add/captain")
	public String addCaptainTeam(Model model, @PathVariable(value="id") long id,@RequestParam("playerId") long playerId) {				
		
		teamService.updateCaptainInTeam(id,playerId);	
		model.addAttribute("message", "team member added as captain successfully");
		return "redirect:/team/detail?id="+id;
	}

	
}
