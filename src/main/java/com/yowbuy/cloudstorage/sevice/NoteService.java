package com.yowbuy.cloudstorage.sevice;

import com.yowbuy.cloudstorage.mapper.NoteMapper;
import com.yowbuy.cloudstorage.model.Note;
import com.yowbuy.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final NoteMapper noteMapper;
    private final UserService userService;

    public NoteService(NoteMapper noteMapper, UserService userService) {
        this.noteMapper = noteMapper;
        this.userService = userService;
    }

    public int addNote(Note note, String username){
        User user = userService.getUser(username);
        if(user == null){
            return 0;
        }
        note.setUserId(user.getUserid());
        return noteMapper.addNote(note);
    }
    public List<Note> getNotes(Integer userId){
        return noteMapper.getNotes(userId);
    }
    public int editNote(Note note){
        return noteMapper.updateNote(note);
    }
    public int deleteNote(Integer noteId){
        return noteMapper.deleteNote(noteId);
    }
}
