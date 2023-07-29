package com.spoonsors.spoonsorsserver.service.bMember;

import com.spoonsors.spoonsorsserver.entity.BMember;
import com.spoonsors.spoonsorsserver.entity.Fridge;
import com.spoonsors.spoonsorsserver.entity.bMember.FridgeDto;
import com.spoonsors.spoonsorsserver.repository.*;
import com.spoonsors.spoonsorsserver.service.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class FridgeService {

    private final FridgeRepository fridgeRepository;
    private final IbMemberRepository ibMemberRepository;
    private final IFridgeRepository iFridgeRepository;

    public Fridge addFridgeItem(String bMemberId, FridgeDto fridgeDto, MultipartFile img) throws IOException {
        log.info("멤버 아이디: {}", bMemberId);
        Optional<BMember> optionalBMember =ibMemberRepository.findById(bMemberId);
        BMember bMember = optionalBMember.get();
        Fridge addFridgeItem=iFridgeRepository.save(fridgeDto.toEntity());
        addFridgeItem.setBMember(bMember);

        if(img!=null && !img.isEmpty()){
            addFridgeItem.setFridge_item_img(ImageUtils.compressImage(img.getBytes()));
        }
        return addFridgeItem;
    }

    public byte[] downloadImage(Long fridge_id) {
        Fridge fridge = fridgeRepository.findById(fridge_id);
        return ImageUtils.decompressImage(fridge.getFridge_item_img());
    }


    public List<Fridge> getFridgeDto(String bMemberId){
        List<Fridge> fridgeItems = fridgeRepository.getFridgeItems(bMemberId);
        return fridgeItems;
    }

    public void removeFridge(Long fridge_id){
        fridgeRepository.deleteFridgeItem(fridge_id);
    }

}
