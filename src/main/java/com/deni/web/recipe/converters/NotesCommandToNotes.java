package com.deni.web.recipe.converters;

import com.deni.web.recipe.commands.NotesCommand;
import com.deni.web.recipe.model.Notes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesCommandToNotes implements Converter<NotesCommand,Notes> {
    @Nullable
    @Override
    public Notes convert(NotesCommand source) {
        if(source==null)
            return null;
        Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNote(source.getRecipeNote());

        return notes;
    }
}
