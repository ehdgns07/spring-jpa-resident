package com.nhnacademy.jpa.service.death;

import com.nhnacademy.jpa.domain.DeathReportDto;
import com.nhnacademy.jpa.entity.birth.death.report.resident.BirthDeathReportResident;
import com.nhnacademy.jpa.entity.resident.Resident;
import com.nhnacademy.jpa.repository.birthDeath.BirthDeathReportRepository;
import com.nhnacademy.jpa.repository.resident.ResidentRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeathReportServiceImpl implements DeathReportService {

	private final BirthDeathReportRepository birthDeathReportRepository;
	private final ResidentRepository residentRepository;

	@Override
	public BirthDeathReportResident createDeathReport(DeathReportDto deathReportDto,
	                                                  Integer serialNumber) {
		BirthDeathReportResident.Pk pk = new BirthDeathReportResident.Pk();
		pk.setReportResidentSerialNo(serialNumber);
		pk.setBirthDeathCode(deathReportDto.getBirthDeathCode());
		pk.setResidentSerialNo(deathReportDto.getResidentSerialNo());


		BirthDeathReportResident deathReportResident = BirthDeathReportResident.builder()
			                                            .pk(pk)
                                                        .reportDate(deathReportDto.getReportDate())
			                                            .deathReportQualificationsCode(deathReportDto.getDeathReportQualificationsCode())
			                                            .emailAddress(deathReportDto.getEmailAddress())
			                                            .phoneNumber(deathReportDto.getPhoneNumber())
			                                            .build();

		Optional<Resident> resident = residentRepository.findById(serialNumber);
		deathReportResident.setResident(resident.orElse(null));

		return birthDeathReportRepository.save(deathReportResident);
	}
}