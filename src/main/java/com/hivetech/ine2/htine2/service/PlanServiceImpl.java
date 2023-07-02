package com.hivetech.ine2.htine2.service;

import com.hivetech.ine2.htine2.model.storage.entity.Description;
import com.hivetech.ine2.htine2.model.storage.entity.Plan;
import com.hivetech.ine2.htine2.repository.DescriptionRepository;
import com.hivetech.ine2.htine2.repository.PlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService{
    @Autowired
    private PlanRepository planRepository;
    @Autowired
    private Converter converter;
    @Autowired
    private DescriptionRepository descriptionRepository;
    @Override
    public ResponseEntity validateTitle(String title) {
        if(title.equals("") || title==null){
            return ResponseEntity.badRequest().body("Title cannot be blank.");
        }
        if (title.startsWith(" ") || title.endsWith(" ")) {
                return ResponseEntity.badRequest().body("Title without starting and ending spaces");
        } else {
            for (int i = 0; i < title.length(); i++) {
                char character = title.charAt(i);
                if (character != 32 && !Character.isLetter(character) && !Character.isDigit(character)) {
                    return ResponseEntity.badRequest().body("Title must not contain characters '"+character+"'");
                }
            }
        }
       return ResponseEntity.ok("");
    }

    @Override
    public ResponseEntity save(Plan planRequest) {
        ResponseEntity title =  validateTitle(planRequest.getTitle());
        if(!title.getBody().equals("")){
            return title;
        }
        if(planRequest.getDescription().getContent().equals("") || planRequest.getDescription().getContent() == null){
            return ResponseEntity.badRequest().body("Description cannot be blank.");
        }
        if(planRequest.getItemLimit() < 0 ){
            return ResponseEntity.badRequest().body("Only accept numbers >= 0");
        }
        if(planRequest.getOrderLimit() < 0 ){
            return ResponseEntity.badRequest().body("Only accept numbers >= 0");
        }
        if(planRequest.getTrialPeriod() < 0 ){
            return ResponseEntity.badRequest().body("Only accept numbers >= 0");
        }
        if(planRequest.getPrice() < 0 ){
            return ResponseEntity.badRequest().body("Only accept numbers >= 0");
        }
        if(planRequest.getPromoPrice() < 0 ){
            return ResponseEntity.badRequest().body("Only accept numbers >= 0");
        }
        Description description = new Description();
        description.setContent( planRequest.getDescription().getContent());
        description = descriptionRepository.save(description);
        planRequest.setDescription(description);
        planRequest.setTitle(converter.convertName(planRequest.getTitle()));
        planRepository.save(planRequest);
        return ResponseEntity.ok("Insert Success");
    }
}
