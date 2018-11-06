package com.example.demo.service;

import com.example.demo.model.AyMood;

/**
 * service层
 */
public interface AyMoodService {
    AyMood save(AyMood ayMood);
    String asynSave(AyMood ayMood);
}
