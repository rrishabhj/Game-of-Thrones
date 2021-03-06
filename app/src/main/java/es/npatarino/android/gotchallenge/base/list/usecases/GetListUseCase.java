/*
 * Copyright 2015 Antonio López Marín <tonilopezmr.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package es.npatarino.android.gotchallenge.base.list.usecases;

import es.npatarino.android.gotchallenge.base.detail.usecases.UseCase;
import es.npatarino.android.gotchallenge.base.list.repository.ListRepository;
import rx.Observable;
import rx.Scheduler;

import java.util.List;

public class GetListUseCase<T> extends UseCase<List<T>> {

    protected final ListRepository<T> repository;

    public GetListUseCase(ListRepository<T> repository,
                          Scheduler uiThread,
                          Scheduler executorThread) {
        super(uiThread, executorThread);
        this.repository = repository;
    }

    @Override
    protected Observable<List<T>> buildUseCaseObservable() {
        return scheduleOn(repository.getList());
    }
}
