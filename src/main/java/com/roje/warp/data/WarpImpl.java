package com.roje.warp.data;

import java.util.List;

public interface WarpImpl {

    void create();
    void delete();
    void teleport();
    List<String> list();
    String getLocation(String name);
}
