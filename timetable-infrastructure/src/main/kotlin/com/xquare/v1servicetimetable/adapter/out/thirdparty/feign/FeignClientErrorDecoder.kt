package com.xquare.v1servicetimetable.adapter.out.thirdparty.feign

import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error.FeignBadRequestException
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error.FeignExpiredTokenException
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error.FeignForbiddenException
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error.FeignInternalServerError
import com.xquare.v1servicetimetable.adapter.out.thirdparty.feign.error.FeignUnAuthorizedException
import feign.FeignException
import feign.Response
import feign.codec.ErrorDecoder

class FeignClientErrorDecoder : ErrorDecoder {

    override fun decode(methodKey: String, response: Response): Exception {
        if (response.status() >= 400) {
            when (response.status()) {
                400 -> throw FeignBadRequestException
                401 -> throw FeignUnAuthorizedException
                403 -> throw FeignForbiddenException
                419 -> throw FeignExpiredTokenException
                else -> throw FeignInternalServerError
            }
        }

        return FeignException.errorStatus(methodKey, response)
    }
}
