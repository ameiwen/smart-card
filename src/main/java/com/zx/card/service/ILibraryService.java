package com.zx.card.service;

import com.github.pagehelper.Page;
import com.zx.card.model.Library;
import com.zx.card.utils.Result;

public interface ILibraryService {

    Result selectLibraryByPage(Page<Library> page, Library library);

    Result saveLibrary(Library library);

    Library selectLibrary(Long id);

    Result updateLibrary(Library library);

}
