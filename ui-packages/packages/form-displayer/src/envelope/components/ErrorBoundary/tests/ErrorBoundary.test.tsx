/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
import React from 'react';
import { mount } from 'enzyme';
import ErrorBoundary from '../ErrorBoundary';
import FormErrorsWrapper from '../../FormErrorsWrapper/FormErrorsWrapper';

jest.mock('../../FormErrorsWrapper/FormErrorsWrapper');

const BuggyComponent = (): React.ReactElement => {
  throw new Error('test error!');
  return <></>;
};

describe('ErrorBoundary tests', () => {
  it('Regular rendering', () => {
    const wrapper = mount(
      <ErrorBoundary notifyOnError={jest.fn()}>
        <h1>Hello world!</h1>
      </ErrorBoundary>
    );

    expect(wrapper).toMatchSnapshot();

    const errorWrapper = wrapper.find(FormErrorsWrapper);
    expect(errorWrapper.exists()).toBeFalsy();

    expect(wrapper.html()).toContain('Hello world!');
  });

  it('Error rendering', () => {
    const wrapper = mount(
      <ErrorBoundary notifyOnError={jest.fn()}>
        <BuggyComponent />
      </ErrorBoundary>
    );

    expect(wrapper).toMatchSnapshot();

    const errorWrapper = wrapper.find(FormErrorsWrapper);
    expect(errorWrapper.exists()).toBeTruthy();
  });
});
