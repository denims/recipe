package com.deni.test.recipe.converters;

import com.deni.test.recipe.commands.NotesCommand;
import com.deni.test.recipe.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesToNotesCommand implements Converter<Notes,NotesCommand> {
    @Nullable
    @Override
    public NotesCommand convert(Notes source) {
        if(source==null)
            return null;
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNote(source.getRecipeNote());
        return notesCommand;
    }
}
