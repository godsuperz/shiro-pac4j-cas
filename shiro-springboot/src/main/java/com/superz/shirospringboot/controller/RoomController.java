package com.superz.shirospringboot.controller;

import com.superz.shirospringboot.common.entity.ResponseResult;
import com.superz.shirospringboot.entity.vo.RoomVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author zhang.chao
 * @date 2021/1/6
 */
@RestController
@RequestMapping("/api/room")
public class RoomController {

    @GetMapping("/getRoomList")
    public ResponseResult<?> getRoomList() {
        List<RoomVO> roomVOList = new ArrayList<>();
        long count = 0;
        while (count++ < 10) {
            RoomVO roomVO = new RoomVO();
            roomVO.setId(count);
            roomVO.setRoomNo("A" + count);
            roomVO.setHouseType("大床房");
            roomVO.setFloorNo("8楼");
            roomVOList.add(roomVO);
        }
        return ResponseResult.success(Collections.singletonMap("roomList", roomVOList));
    }
}
