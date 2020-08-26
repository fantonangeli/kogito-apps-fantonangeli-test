import React from 'react';
import Breadcrumbs from '../Breadcrumbs';
import { mount } from 'enzyme';
import { MemoryRouter } from 'react-router-dom';

describe('Breadcrumbs', () => {
  test('renders correctly', () => {
    const wrapper = mount(
      <MemoryRouter initialEntries={['/audit']}>
        <Breadcrumbs />
      </MemoryRouter>
    );
    const breadcrumbs = wrapper.find(Breadcrumbs);

    expect(breadcrumbs).toMatchSnapshot();
    expect(breadcrumbs.find('li.breadcrumb-item')).toHaveLength(0);
  });

  test('renders outcome details breadcrumbs links', () => {
    const executionId = 'b2b0ed8d-c1e2-46b5-3ac54ff4beae-1000';
    const wrapper = mount(
      <MemoryRouter
        initialEntries={[
          {
            pathname: `/audit/decision/${executionId}/outcomes`,
            key: 'execution'
          }
        ]}
      >
        <Breadcrumbs />
      </MemoryRouter>
    );
    const breadcrumbs = wrapper.find(Breadcrumbs);

    expect(breadcrumbs).toMatchSnapshot();
    expect(breadcrumbs.find('li.breadcrumb-item')).toHaveLength(3);
    expect(
      breadcrumbs
        .find('li.breadcrumb-item')
        .at(0)
        .text()
    ).toMatch('Audit Investigation');
    expect(
      breadcrumbs
        .find('li.breadcrumb-item')
        .at(1)
        .text()
    ).toMatch(`ID #${executionId}`);
    expect(
      breadcrumbs
        .find('li.breadcrumb-item')
        .at(2)
        .text()
    ).toMatch('Outcomes');
    expect(
      breadcrumbs
        .find('li.breadcrumb-item')
        .find('.pf-c-breadcrumb__link.pf-m-current')
    ).toHaveLength(1);
  });
});
